import React, { useState,useContext, useRef } from "react";
import noteContext from "../context/notes/NoteContext";
import NoteItem from "./NoteItem";
import AddNote from "./AddNote";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
const Notes = () => {
  const context = useContext(noteContext);
  const { notes, fetchAllNotes,editNote } = context;
  const ref = useRef("");
  const refClose =useRef(null)
  const [note,setnote] =useState({e_id:'',title:'',edescription:'',etag:''})
  const history = useNavigate()
  useEffect(() => {
    if(localStorage.getItem('token')){
      fetchAllNotes();
    }
    else{
      history("/login")
    }
    
    // eslint-disable-next-line
  }, []);
  const updateNotes = (currentNote) => {
    ref.current.click();
    setnote({e_id:currentNote._id,etitle:currentNote.title,edescription:currentNote.description,etag:currentNote.tag})
  };
  const handleChange = (e)=>{
    setnote({...note,[e.target.name]:e.target.value})
}
const handleClick = (e)=>{
    e.preventDefault()
    editNote(note.e_id,note.etitle,note.edescription,note.etag )
    refClose.current.click();
  }
  return (
    <>
      <AddNote></AddNote>
      <h1>Your Notes</h1>
      <button
        type="button"
        className="btn btn-primary d-none "
        data-bs-toggle="modal"
        data-bs-target="#exampleModal"
        ref={ref}
      >
        Launch demo modal
      </button>

      <div
        className="modal fade"
        id="exampleModal"
        tabIndex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                Edit Note
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <form className="container">
                <div className="container my-5">
                  <div className="mb-3">
                    <label htmlFor="etitle" className="form-label">
                      Title
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="etitle"
                      onChange={handleChange}
                      name="etitle"
                      value={note.etitle}
                    />
                  </div>
                  <div className="mb  -3">
                    <label htmlFor="edescription" className="form-label">
                      Description
                    </label>
                    <textarea
                      className="form-control"
                      id="edescription"
                      rows="2"
                      name="edescription"
                      onChange={handleChange}
                      value={note.edescription}
                    ></textarea>
                  </div>
                  <div className="mb  -3">
                    <label htmlFor="etag" className="form-label">
                      Tag
                    </label>
                    <textarea
                      className="form-control"
                      id="etag"
                      rows="1"
                      name="etag"
                      onChange={handleChange}
                      value={note.etag}
                    ></textarea>
                  </div>
                </div>
              </form>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
              <button type="button" className="btn btn-primary" data-bs-dismiss="modal" onClick={handleClick} ref={refClose}>
                Save changes
              </button>
            </div>
          </div>
        </div>
      </div>
      <div className="row my-3">
        {notes.map((ele) => {
          return (
            <NoteItem key={ele._id} note={ele} updateNotes={updateNotes} />
          );
        })}
      </div>
    </>
  );
};

export default Notes;
