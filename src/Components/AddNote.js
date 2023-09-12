import React, { useContext, useState } from "react";
import noteContext from "../context/notes/NoteContext";

const AddNote = () => {
    const context = useContext(noteContext)
    const {addNote} = context
    const [note,setnote] =useState({title:'',description:'',tag:''})
    const handleChange = (e)=>{
        setnote({...note,[e.target.name]:e.target.value})
    }
    const handleClick = (e)=>{
        e.preventDefault()
        addNote(note.title,note.description,note.tag )
    }
  return (
    <div>
          <h1>Add a Note</h1>
      <form className="container">
        <div className="container my-5">
          <div className="mb-3">
            <label htmlFor="title" className="form-label">
              Title
            </label>
            <input type="text" className="form-control" id="title" onChange={handleChange} name="title"/>
          </div>
          <div className="mb  -3">
            <label htmlFor="description" className="form-label" >
              Description
            </label>
            <textarea
              className="form-control"
              id="description"
              rows="3"
              name="description"
              onChange={handleChange}
            ></textarea>
          </div>
          <div className="mb  -3">
            <label htmlFor="tag" className="form-label" >
              Tag
            </label>
            <textarea
              className="form-control"
              id="tag"
              rows="1"
              name="tag"
              onChange={handleChange}
            ></textarea>
          </div>
          <div className="col-12">
            <button
              type="submit"
              className="btn btn-primary my-3"
              onClick={handleClick}
            >
              Submit
            </button>
          </div>
          
        </div>
      </form>
    </div>
  );
};

export default AddNote;
