import { useEffect, useRef, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import TextLogo from "../../Images/TextLogo.png";
import SmallLogo from "../../Images/SmallLogo.png";
import "./Navbar.css";

function Navbar() {
  return (
    <>
      <div className="rootDiv">
        <div>
          <img className="logoImage" src={SmallLogo} />
          <img className="logoImage" src={TextLogo} />
        </div>
        <br />
        <br />
        <div>
          <button className="navButton flights">Flights</button>
          <button className="navButton hotels">Hotels</button>
          <button className="navButton attractions">Attractions</button>
          <button className="navButton yourTrip">Your Trip</button>
        </div>
        <div>
          <hr
            style={{
              background: "grey",
              color: "grey",
              borderColor: "grey",
              height: "1.25px",
              width: "250px",
              marginLeft: "22px",
            }}
          />
        </div>
        <div className="buttonTextGrey">
            <button className="bottomButtons">Vacation Summary</button>
            <br/>
            <br/>
            <button className="bottomButtons">Budget Calculator</button>
            <br/>
            <br/>
            <button className="bottomButtons">Savings Plan</button>
        </div>
      </div>
    </>
  );
}

export default Navbar;
