import os

output = []
wiki_dir = r"MinecoloniesWiki\src\content\wiki"

for root, dirs, files in os.walk(wiki_dir):
    for file in files:
        if file.endswith(".mdoc"):
            filepath = os.path.join(root, file)
            with open(filepath, "r", encoding="utf-8", errors="ignore") as f:
                content = f.read()
            output.append(f"\n\n---\n# File: {filepath}\n\n{content}")

with open("minecolonies_wiki.md", "w", encoding="utf-8") as f:
    f.write("\n".join(output))

print(f"Done! {len(output)} files combined.")