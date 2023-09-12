import React, { useState } from "react";
import noteContext from "./NoteContext";
const NoteState = (props) => {
  const url = "http://localhost:5000";
  const noteIntital = {
    Message: "Notes Feteched Successfully",
    notes: [],
  };
  const [notes, setNotes] = useState(noteIntital.notes);

  // Fetch All Notes
  const fetchAllNotes = async () => {
    //Fetch Api
    const response = await fetch(`${url}/api/note/fetchNotes`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "auth-token": localStorage.getItem('token'),
      },
    });
    const res = await response.json();
    setNotes(res.notes);
  };
  // Add Note
  const addNote = async (title, description, tag) => {
    //Fetch Api
    const response = await fetch(`${url}/api/note/addNotes`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "auth-token": localStorage.getItem('token'),
      },
      body: JSON.stringify({
        title: title,
        description: description,
        tag: tag,
      }),
    });
    const res = await response.json();
    setNotes(notes.concat(res.notes));
  };

  // Edit Note
  const editNote = async (id, title, description, tag) => {
    //Fetch Api
    const response = await fetch(`${url}/api/note/updateNotes/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        "auth-token": localStorage.getItem('token'),
      },
      body: JSON.stringify({
        title: title,
        description: description,
        tag: tag,
      }),
    });
    const newnotes = JSON.parse(JSON.stringify(notes)) 
    for (let i = 0; i < newnotes.length; i++) {
      const ele = notes[i];
      if (ele._id === id) {
        newnotes[i].title = title 
        newnotes[i].tag = tag
        newnotes[i].description = description
        break
      }
    }
    setNotes(newnotes)
  };
  // Delete Note
  const deleteNote = async (id) => {
    // // Fetch Api
    const response = await fetch(`${url}/api/note/deleteNotes/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "auth-token": localStorage.getItem('token'),
      },
    });
    const newNotes = notes.filter((ele) => {
      return ele._id !== id;
    });
    setNotes(newNotes);
  };
  return (
    <noteContext.Provider value={{ notes, addNote, deleteNote, fetchAllNotes, editNote }}>
      {props.children}
    </noteContext.Provider>
  );
};

export default NoteState;
