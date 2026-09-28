# Chimp

An offline-first flashcard application for Android, designed to make studying simple, fast, and free.

## Overview

Chimp is a mobile flashcard application built for students who value the simplicity of paper flashcards but not the burden of carrying and maintaining them. Active recall is a well-established and effective study method, yet physical cards are easily misplaced, tedious to organize, and impossible to back up. Existing digital alternatives address some of these problems, but many introduce new ones, such as mandatory subscriptions, a required internet connection, or complex interfaces that distract from studying.

Chimp takes a deliberately focused approach. Users create their own decks, review them through a tap-to-flip interaction, and monitor their progress, all while completely offline. Because every deck and card is stored locally on the device, the application functions anywhere, without an account or a network connection. Optional online backup is planned for a future release and is not part of the current version.

## Features

**Core**
- Deck and card creation. Users build custom decks organized by subject, with text on the front and back of each card.
- Tap-to-flip review. Cards are studied one at a time using a simple flip interaction that mirrors physical flashcards.
- Recall rating. Each card is marked as Got It or Review Again, and missed cards are re-queued within the same session.
- Progress and streaks. The application tracks a daily study streak and displays mastered and pending cards for each deck.
- Match game. A term-matching mini game provides an alternative and more engaging way to review a deck.

**Supporting**
- Deck management. Users can shuffle, rename, delete, and reset the progress of a deck.
- Card editing. Individual cards within a deck can be updated or removed.

**Excluded from this release.** The following features are intentionally deferred or considered out of scope: account creation and cloud synchronization, deck sharing, spaced-repetition scheduling, image and audio support, and any form of advertising or paid tier. These boundaries keep the initial release simple and consistent with the offline-first goal.

## Technology Stack

- Kotlin
- Jetpack Compose with Material 3 for the user interface
- Gradle with the Kotlin DSL
- Local on-device storage for decks and cards
- ViewModel and Navigation Compose

## Requirements

- Android Studio, latest stable version recommended
- JDK 17
- Android SDK, minimum SDK 24
- An Android emulator or a physical Android device

## Getting Started

1. Clone the repository.
```bash
   git clone <repository-url>
```
2. Open the project in Android Studio. Select the root folder rather than a subfolder.
3. Allow the Gradle sync to complete. Android Studio will download the required dependencies automatically.
4. Select an emulator or connect a physical device.
5. Run the application from Android Studio, or from the terminal:
```bash
   ./gradlew installDebug
```

## Project Structure

```text
mobdev-chimp/
├── app/                  # main application module (Kotlin and Compose source)
├── gradle/               # Gradle wrapper files
├── build.gradle.kts      # project-level Gradle configuration
├── settings.gradle.kts   # module settings
├── gradle.properties     # Gradle properties
├── gradlew / gradlew.bat # Gradle wrapper scripts
├── CONTRIBUTING.md       # team workflow and contribution guide
└── README.md
```

## Screenshots

Screenshots will be added once the interface screens are finalized. The dashboard, study flashcard, and match game screens are recommended, for example:

```markdown
![Dashboard](docs/screenshots/dashboard.png)
![Study](docs/screenshots/study.png)
![Match Game](docs/screenshots/match.png)
```

## Contributing

This project follows a shared workflow so that all members remain in sync. Please review [CONTRIBUTING.md](CONTRIBUTING.md) before beginning any task. In summary, base your work on the `dev` branch, create a dedicated task branch, commit with clear and descriptive messages, and open a pull request that targets `dev`.

## Team

- John Raymund Apostol
- Ritz Vincent Culanag
- Ethan Malcom Porte
- Aleczandra Tulod

## License

This project was developed as a course requirement for educational purposes.
