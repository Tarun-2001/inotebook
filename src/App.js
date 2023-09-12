import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./Components/Navbar";
import Home from "./Components/Home";
import About from "./Components/About";
import NoteState from './context/notes/NoteState'
import LoginPage from "./Components/LoginPage";
import SignUp from "./Components/SignUp";
function App() {
  return (
    <>
      <NoteState>
        <Router>
          <Navbar />
          <div className="container">
            <Routes>
              <Route exact path="/" element={<Home />} />
              <Route exact path="/about" element={<About />} />
              <Route exact path="/login" element={<LoginPage />} />
              <Route exact path="/signup" element={<SignUp />} />
              
            </Routes>
          </div>
          
        </Router>
      </NoteState>
    </>
  );
}

export default App;
