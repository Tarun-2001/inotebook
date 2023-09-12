import React from "react";
import noteContext from "../context/notes/NoteContext";
import { useContext } from "react";

const NoteItem = (props) => {
  const { note,updateNotes } = props;
  const context = useContext(noteContext);
  const { deleteNote } = context;

  return (
    <div className="col-md-3 my-3 ">
      <div className="card" style={{ width: "18rem" }}>
        <div className="card-body ">
          <div className="d-flex ">
            <h5
              className="card-title"
              style={{ display: "inline-block", width: "80%", height: "10px" }}
            >
              {note.title}
            </h5>
            <i
              className="fa-solid fa-trash-can mx-2"
              onClick={()=>{deleteNote(note._id)}}
            ></i>
            <i className="fa-solid fa-pen-to-square mx-2 "
            onClick={()=>{updateNotes(note)}}></i>
          </div>
          <p className="card-text">{note.description}</p>
          {/* <p className="card-text">{note.tag}</p> */}
          
        </div>
      </div>
    </div>
  );
};

export default NoteItem;
