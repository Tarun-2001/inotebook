import React, { useState } from "react";
import noteContext from "./NoteContext";
const NoteState = (props) => {
  const noteIntital = {
    Message: "Notes Feteched Successfully",
    notes: [
      {
        _id: "64fa179461abeb3e7c9b7340",
        user: "64f82bb66c5c505e6b14ba0f",
        title: "Note 1",
        description: "Note by user 1",
        tag: "General",
        date: "2023-09-07T18:33:56.516Z",
        __v: 0
      },
     
    ],
  };
  const [notes,setNotes] = useState(noteIntital.notes)
   // Add Note
   const addNote = (title, description, tag)=>{
    const note={
      _id: "64fa179w461abeb83qe7c9b7340",
      user: "64f82bb66c5c505e6b14ba0f",
      title: title,
      description: description,
      tag: tag,
      date: "2023-09-07T18:33:56.516Z",
      __v: 0
    }
    
    setNotes(notes.concat(note))
    // console.log(notes.notes.concat(note))
   }
   // Edit Note
   const editNote = ()=>{
 
   }
   // Delete Note
   const deleteNote = (id)=>{
    // console.log("Deleting note " +id)
    const delnote = notes.filter((ele)=>{ return ele._id!=id})
    setNotes(delnote)
   }
  return (
    <noteContext.Provider value={{notes,addNote,deleteNote}}>{props.children}</noteContext.Provider>
  );
};

export default NoteState;
