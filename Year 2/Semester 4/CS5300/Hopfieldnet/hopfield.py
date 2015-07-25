import sys
import numpy as np

class HopfieldNetwork(object):
    def __init__(self, dim):
        self.dim = dim
        self.W = np.zeros((dim, dim))

    def energy(self, x):
        return -0.5 * (self.W * np.outer(x, x)).sum()
    
    def update(self, x):
        p = 2 * x - 1
        self.W += np.outer(p, p)
        self.W[np.diag_indices_from(self.W)] = 0

    def run(self, x, maxiter = 200):
        p = 2 * x - 1
        i = 0
        conctr = 0
        eold = sys.maxint
        while i < maxiter:
            k = np.random.randint(0, self.dim)
            net = np.dot(self.W[k], p)
            if net >= 0:
                p[k] = 1
            else:
                p[k] = -1
            i += 1
            e = self.energy(p)
            if e < eold:
                conctr = 0
                eold = e
            else:
                conctr += 1
                if conctr > 30:
                    break
        return (p > 0).astype(int)

def flipbits(x, prob = 0.1):
    y = np.array([not b if np.random.random() < prob else b for b in x])
    y = y.astype(int)
    return y

if __name__ == '__main__':
    Input = raw_input("enter 10 chars")
    print Input
    
    f = [(bin(ord(i))[2:]) for i in Input]
    print f
    dim = 10
    X = np.random.randint(0, 2, (2, 10))
    hopnet = HopfieldNetwork(dim)
    for x in X:
        hopnet.update(x)
    
    for x in X:
        noisyx = flipbits(x)
        output = hopnet.run(noisyx)
        print x, output




