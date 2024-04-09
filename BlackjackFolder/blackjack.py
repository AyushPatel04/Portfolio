from deck import Deck
from hand import Hand

class Blackjack:
    def __init__(self):
        self.deck = Deck()
        self.deck.shuffle()
        self.player_hand = Hand()
        self.dealer_hand = Hand()
        self.game_over = False
    
    def deal_starting_hands(self):
        self.player_hand = Hand()  # Reset hands for a new game
        self.dealer_hand = Hand()
        for _ in range(2):  # Deal two cards to each player
            self.player_hand.add_card(self.deck.deal())
            self.dealer_hand.add_card(self.deck.deal())
    
    def show_hands(self, reveal_dealer=False):
        print("\nPlayer's hand:", *self.player_hand.cards, "| Value:", self.player_hand.value)
        if reveal_dealer:
            print("Dealer's hand:", *self.dealer_hand.cards, "| Value:", self.dealer_hand.value)
        else:
            print("Dealer's hand: [", self.dealer_hand.cards[0], ", ? ]")
    
    def player_choice(self):
        while self.player_hand.value < 21:
            action = input("Do you want to (h)it or (s)tand? ").lower().strip()
            if action in ['h', 'hit']:
                self.player_hits()
                self.show_hands()
            elif action in ['s', 'stand']:
                print("Player stands.")
                break
    
    def player_hits(self):
        card = self.deck.deal()
        if card:
            self.player_hand.add_card(card)
            print("Player hits:", card)
            if self.player_hand.value > 21:
                self.game_over = True
                print("Player busts!")
    
    def dealer_plays(self):
        while self.dealer_hand.value < 17:
            card = self.deck.deal()
            if card:
                self.dealer_hand.add_card(card)
                print("Dealer hits:", card)
        if self.dealer_hand.value > 21:
            print("Dealer busts!")
    
    def check_winner(self):
        if self.player_hand.value > 21:
            return "Dealer wins."
        elif self.dealer_hand.value > 21 or self.player_hand.value > self.dealer_hand.value:
            return "Player wins!"
        elif self.player_hand.value < self.dealer_hand.value:
            return "Dealer wins."
        else:
            return "It's a tie!"
    
    def play(self):
        while True:
            self.game_over = False
            self.deck = Deck()  # Reinitialize the deck and shuffle for a new game
            self.deck.shuffle()
            self.deal_starting_hands()
            self.show_hands()
            self.player_choice()
            if not self.game_over:
                self.dealer_plays()
                self.show_hands(reveal_dealer=True)
            print(self.check_winner())
            if input("Play again? (y/n): ").lower() != 'y':
                break

# To play the game, instantiate the Blackjack class and call the play method
if __name__ == "__main__":
    game = Blackjack()
    game.play()
