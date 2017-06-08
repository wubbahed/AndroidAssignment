# Android Assignment

This repository contains a very simple, Android implementation of the game [Rock, Paper Scissors](https://en.wikipedia.org/wiki/Rock%E2%80%93paper%E2%80%93scissors).

There are only two activities in this app. The main activity is a lobby where it shows all devices that are currently online.  Once you choose your opponent (or someone chooses you), then you proceed to the second activity which is the game.  In the game activity, you play a round, and then the game automatically resets.  Play as long as you want.

The app is built with a basic MVP structure (Model-View-Presenter) and makes heavy use of these technologies:
* [Firebase](https://firebase.google.com/docs/database/android/read-and-write) - Firebase is used as a real-time backend to support communication between the two players.
* [Dagger2](https://google.github.io/dagger/android.html) - Dagger2 is used for dependency injection within the application
* [ButterKnife](http://jakewharton.github.io/butterknife/) - ButterKnife is used for finding fields and binding methods to them within Activities

---
Your assignment has two parts:
## Part 1: Code Review
Review the code for:
* Architecture
* Coding Style
* Logic & Lifecycle bugs
* Code Optimzation & Performance

## Part 2: Feature Implementation
