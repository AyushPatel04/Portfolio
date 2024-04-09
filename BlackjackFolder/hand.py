from card import Card

class Hand:
    def __init__(self):
        self.cards = []
        self.value = 0
        self.aces = 0
    
    def add_card(self, card):
        self.cards.append(card)
        self.adjust_for_ace()
    
    def adjust_for_ace(self):
        self.value = sum([11 if card.rank == 'Ace' else 10 if card.rank in ['Jack', 'Queen', 'King'] else int(card.rank) for card in self.cards])
        self.aces = sum([1 for card in self.cards if card.rank == 'Ace'])
        
        while self.value > 21 and self.aces:
            self.value -= 10
            self.aces -= 1