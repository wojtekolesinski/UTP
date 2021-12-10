import random

with open('Towary.txt', 'w') as towary:
    towary.writelines(
            (f'{id} {(random.random() * 1000)}\n' for id in range(1, 10_001))
        )
