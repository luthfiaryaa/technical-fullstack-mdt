import React, { createContext, useState } from 'react';

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [isLoggedIn, setIsLoggedIn] = useState(false); 

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setUser(null); 
  };

  return (
    <UserContext.Provider value={{ user, setUser, isLoggedIn, handleLogin, handleLogout }}>
      {children}
    </UserContext.Provider>
  );
};
