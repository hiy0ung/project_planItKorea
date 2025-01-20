import React from 'react'
import { create } from 'zustand';

interface AuthUser {
  token: string;
}

interface AuthStore {
  isLoggedIn: boolean;
  user: AuthUser | null;

  login: (user: AuthUser) => void;
  logout: () => void;
}

const useAuthStore = create<AuthStore>((set) => ({
  isLoggedIn: false,
  user: null,

  login: (user: AuthUser) => set({ isLoggedIn: true, user }),
  logout: () => set({ isLoggedIn: false, user: null }),
}));
export default useAuthStore;