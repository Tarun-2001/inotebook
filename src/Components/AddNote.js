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
        addNote("note"," ","d" )
    }
  return (
    <div>
      <form className="container">
        <div className="container my-5">
          <h1>Add a Note</h1>
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
          <div className="col-12">
            <div className="form-check">
              <input
                className="form-check-input"
                type="checkbox"
                id="gridCheck"
              />
              <label className="form-check-label" htmlFor="gridCheck">
                Check me out
              </label>
            </div>
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
