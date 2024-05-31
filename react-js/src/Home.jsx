import React, { useContext, useEffect } from 'react';
import { UserContext } from './UserContext';
import { useNavigate } from 'react-router-dom';

const Home = () => {
  const { user, handleLogout } = useContext(UserContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user) {
      navigate('/login');
    }
  }, [user, navigate]);

  const handleLogoutClick = () => {
    handleLogout();
    navigate('/login');
  };

  return (
    <div>
      {user ? (
        <div>
          <h1>Welcome {user.username}</h1>
          <button onClick={handleLogoutClick}>Logout</button>
        </div>
      ) : (
        <h1>Welcome</h1>
      )}
    </div>
  );
};

export default Home;
