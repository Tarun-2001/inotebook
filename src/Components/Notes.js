import React, { useContext } from "react";
import noteContext from "../context/notes/NoteContext";
import NoteItem from "./NoteItem";
import AddNote from "./AddNote";
const Notes = () => {
  const context = useContext(noteContext);
  const { notes, addNote } = context;
  return (
    <>
    <AddNote></AddNote>
    <h1>Your Notes</h1>
    <div className="row my-3">
      {notes.map((ele) => {
        return <NoteItem key={ele._id} note={ele} />;
      })}
    </div>
    </>
  );
};

export default Notes;
