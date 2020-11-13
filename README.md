# DeckWars

This is a demonstration project intended to show a concrete example of using both Java Collections and the principles of inheritance to construct a working application.

The general idea is two players hold decks that battle each other to determine a winner and a loser. Ties are also possible.

Due to the structure of the classes, it should be relatively easy to add new cards or different game logic to the application.

# Challenges

- Refactor `AbstractDeck` and the classes that implement it to remove the inheritance of ArrayList, preferring instead to compose the class with a property that is of type ArrayList, keeping track of card contents that way.
    - `AbstractDeck` should still be iterable
    - i.e. the syntax `for (AbstractCard card : deck) {}` should be valid as long as `deck` is an instance of an `AbstractDeck`.


- Fix the bug where the console output will indicate the player is playing a card that they actually couldn't afford to play (for energy/cost reasons)


- Create new logic that allows the main application to run a version of the game that frequently results in a tie.
    - Probably requires several new implementations of concrete classes such as `StandardPlayer` and `CuratedDeck`
    - Try to use as much existing code as possible, although some modifications will likely need to be made that are significant in scope


- Create at least one new implementation of `AttackCard`, `DefenseCard`, or `UtilityCard`. Or one of each!


- Clean up the implementation's use of the `CardClass` enum. It probably shouldn't exist at all. Use the `instanceof` keyword more to check instead for object type.


- Create a more fleshed out command line interface that allows the player to decide between 2 card options or to skip their turn completely.


- Add some options to the game, like allowing the player to pause between each round and display contents of their deck/hand.
