#!/usr/bin/env python3
"""
blueprint_materials_v0_2.py — Extract materials from all .blueprint files in a folder.
Recurses into subfolders. Writes one .json per .blueprint, alongside the source file.

Block indices are packed 32-bit ints: upper 16 bits = layer, lower 16 bits = palette index.
Layer is preserved in JSON for future visualizer use.

Usage: python blueprint_materials_v0_2.py <folder>

Requires: pip install nbtlib
"""

import sys
import json
from pathlib import Path
from collections import Counter

try:
    import nbtlib
except ImportError:
    print("ERROR: nbtlib not installed. Run: pip install nbtlib")
    sys.exit(1)

EXCLUDE = {
    "minecraft:air",
    "structurize:blocksubstitution",
    "structurize:blocksolidsubstitution",
}


def process_blueprint(path: Path):
    nbt = nbtlib.load(str(path))

    size_x = int(nbt["size_x"])
    size_y = int(nbt["size_y"])
    size_z = int(nbt["size_z"])

    # Strip block states from palette entries; keep name only
    palette = [str(entry["Name"]).split("[")[0] for entry in nbt["palette"]]

    # Packed int: upper 16 bits = layer, lower 16 bits = palette index
    max_layer = 0
    counts = Counter()
    for packed in nbt["blocks"]:
        v = int(packed)
        layer = (v >> 16) & 0xFFFF
        idx = v & 0xFFFF
        if layer > max_layer:
            max_layer = layer
        block = palette[idx]
        if block not in EXCLUDE:
            counts[block] += 1

    result = {
        "name": path.stem,
        "size_x": size_x,
        "size_y": size_y,
        "size_z": size_z,
        "max_layer": max_layer,
        "materials": dict(sorted(counts.items())),
    }

    out_path = path.with_suffix(".json")
    with open(out_path, "w", encoding="utf-8") as f:
        json.dump(result, f, indent=2)

    return out_path, len(counts)


def run(folder: str):
    root = Path(folder)
    if not root.is_dir():
        print(f"ERROR: Not a directory: {folder}")
        sys.exit(1)

    blueprints = sorted(root.rglob("*.blueprint"))
    if not blueprints:
        print(f"No .blueprint files found in: {folder}")
        sys.exit(0)

    print(f"\nProcessing {len(blueprints)} blueprint(s) in '{root}'...\n")

    ok = 0
    err = 0
    for bp in blueprints:
        try:
            out_path, block_types = process_blueprint(bp)
            print(f"  OK  {bp.relative_to(root)}  →  {out_path.name}  ({block_types} block types)")
            ok += 1
        except Exception as e:
            print(f"  ERR {bp.relative_to(root)}  —  {e}")
            err += 1

    print(f"\nDone. {ok} succeeded, {err} failed.\n")


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python blueprint_materials_v0_2.py <folder>")
        sys.exit(1)
    run(sys.argv[1])
