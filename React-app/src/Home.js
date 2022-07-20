import {Link,Outlet } from 'react-router-dom';

const Home = () => {
    return(
      
      <div className="wrapper">
        <Link to="/Login">Logout</Link>
        <h2>Application Home</h2>
        <Link to="/Dashboard">Dashboard</Link> |{" "}
        <Link to="/Preferences">Preferences</Link>|{" "}
        <Outlet />
      </div>
    );
  }
  export default Home;