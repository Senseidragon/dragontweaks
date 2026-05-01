Compiled from "NoteBlockEvent.java"
public abstract class net.neoforged.neoforge.event.level.NoteBlockEvent extends net.neoforged.neoforge.event.level.BlockEvent {
  public net.neoforged.neoforge.event.level.NoteBlockEvent$Note getNote();
  public net.neoforged.neoforge.event.level.NoteBlockEvent$Octave getOctave();
  public int getVanillaNoteId();
  public void setNote(net.neoforged.neoforge.event.level.NoteBlockEvent$Note, net.neoforged.neoforge.event.level.NoteBlockEvent$Octave);
}
