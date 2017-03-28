package com.company;

import java.util.*;


public class CardDeck {
    public CardDeck(DeckSize deckSize) {
        this(deckSize, true);
    }

    enum Suit {
        SPADES("♠"),
        CLUBS("♣"),
        DIAMONDS("♦"),
        HEARTS("♥");

        private final String sign;

        Suit(String s) {
            this.sign = s;
        }

        public String getSign() {
            return sign;
        }
    }

    enum DeckSize {D36, D54}

    enum CardValue {
        C2, C3, C4, C5, C6, C7, C8, C9, C10, J, Q, K, A;

        @Override
        public String toString() {
            return super.toString();
        }
    }

    class GameCard {
        private Suit suit;
        private CardValue cardValue;

        public GameCard(Suit suit, CardValue cardValue) {
            this.suit = suit;
            this.cardValue = cardValue;
        }

        @Override
        public String toString() {
            return cardValue + suit.getSign();
        }

        public CardValue getValue() {
            return cardValue;
        }
    }

    private DeckSize deckSize;
    private List<GameCard> cards;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(deckSize).append(" Deck: \n");
        for (GameCard card : cards) {
            stringBuilder.append(card.toString()).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    public CardDeck(DeckSize deckSize, boolean shuffled) {
        this.deckSize = deckSize;
        boolean is36cardDeck = DeckSize.D36.equals(deckSize);
        cards = new ArrayList<>(is36cardDeck ? 36 : 52);
        for (Suit suit : Suit.values()) {
            CardValue[] values = CardValue.values();
            CardValue[] usingCardValues = is36cardDeck
                    ? Arrays.copyOfRange(values, 4, values.length)
                    : values;
            for (CardValue cardValue : usingCardValues) {
                cards.add(new GameCard(suit, cardValue));
            }
        }
        if (shuffled) {
            Collections.shuffle(cards);
        }
    }

    public static void main(String[] args) {
        CardDeck cardDeck36 = new CardDeck(DeckSize.D36, true);
        Comparator<GameCard> cardComparator = (o1, o2) -> {
            int suitDifference = Integer.signum(o2.suit.ordinal() - o1.suit.ordinal());
            if (suitDifference == 0) {
                return Integer.signum(o2.getValue().ordinal() - o1.getValue().ordinal());
            }
            return suitDifference;
        };
        PriorityQueue<GameCard> pl1Cards = new PriorityQueue<>(cardComparator);
        PriorityQueue<GameCard> pl2Cards = new PriorityQueue<>(cardComparator);
        pl1Cards.addAll(cardDeck36.pullCards(6));
        pl2Cards.addAll(cardDeck36.pullCards(6));
        System.out.println(pl1Cards);
        System.out.println(pl2Cards);
    }

    public List<GameCard> pullCards(int numberOfCards) {
        List<GameCard> gameCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            gameCards.add(pullCard());
        }
        return gameCards;
    }

    public GameCard pullCard() {
        return cards.size() > 0 ? cards.remove(0) : null;
    }
}
