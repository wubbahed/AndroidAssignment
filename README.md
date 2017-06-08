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
Review the code and make recommendations for:
* __Architecture__ - Is the architecture of the app clear, readable and scalable?
* __Coding Style__ - Does the coding style make sense? Is it consistent?
* __Logic & Lifecycle bugs__ - The code compiles and runs, but it is not bug-free.  What bugs do you find? How would you recommend fixing them.
* __Code Optimzation & Performance__ - Many of the functions in the code are quick and dirty hacks.  How would you improve or optimize some of the functions in the application?

## Part 2: Feature Implementation

After completing Part 1, define one or two features (or fixes) that you would like to implement in this application, and implement them.  Submit your changes as a pull request.
