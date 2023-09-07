import React, { useContext } from "react";
import noteContext from "../context/notes/NoteContext";
import NoteItem from "./NoteItem";
const Notes = () => {
  const context = useContext(noteContext);
  const { notes, setNotes } = context;
  return (
    <div className="row my-3">
      {notes.notes.map((ele) => {
        return <NoteItem key = {ele.id} note={ele} />;
      })}
    </div>
  );
};

export default Notes;
