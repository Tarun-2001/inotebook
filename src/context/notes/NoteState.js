import React, { useState } from "react";
import noteContext from "./NoteContext";
const NoteState = (props) => {
  const note = {
    Message: "Notes Feteched Successfully",
    notes: [
      {
        _id: "64fa179461abeb3e7c9b7340",
        user: "64f82bb66c5c505e6b14ba0f",
        title: "Note 1",
        description: "Note by user 1",
        tag: "General",
        date: "2023-09-07T18:33:56.516Z",
        __v: 0,
      },
      {
        _id: "64fa179861abeb3e7c9b7342",
        user: "64f82bb66c5c505e6b14ba0f",
        title: "Note 2",
        description: "Note by user 1",
        tag: "General",
        date: "2023-09-07T18:34:00.497Z",
        __v: 0,
      },
      {
        _id: "64fa179861abeb3e7c9b7342",
        user: "64f82bb66c5c505e6b14ba0f",
        title: "Note 2",
        description: "Note by user 1",
        tag: "General",
        date: "2023-09-07T18:34:00.497Z",
        __v: 0,
      },
      {
        _id: "64fa179861abeb3e7c9b7342",
        user: "64f82bb66c5c505e6b14ba0f",
        title: "Note 2",
        description: "Note by user 1",
        tag: "General",
        date: "2023-09-07T18:34:00.497Z",
        __v: 0,
      },
      {
        _id: "64fa179861abeb3e7c9b7342",
        user: "64f82bb66c5c505e6b14ba0f",
        title: "Note 2",
        description: "Note by user 1",
        tag: "General",
        date: "2023-09-07T18:34:00.497Z",
        __v: 0,
      },
      {
        _id: "64fa179861abeb3e7c9b7342",
        user: "64f82bb66c5c505e6b14ba0f",
        title: "Note 2",
        description: "Note by user 1",
        tag: "General",
        date: "2023-09-07T18:34:00.497Z",
        __v: 0,
      }
    ],
  };
  const [notes,setNotes] = useState(note)
  return (
    <noteContext.Provider value={{notes,setNotes}}>{props.children}</noteContext.Provider>
  );
};

export default NoteState;
