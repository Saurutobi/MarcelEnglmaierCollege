import sys
import numpy as np
import time

class HopfieldNetwork(object):
	def __init__(self, dim):
		self.dim = dim
		self.w = np.zeros((dim, dim))
		self.state = np.zeros(dim)

	def energy(self, x):
		return -0.4 * (self.w * np.outer(x, x)).sum()
		
	def update(self, x):
		p = 2 * x - 1
		self.w += np.outer(p, p)
		self.w[np.diag_indices_from(self.w)] = 0

	def run(self, x, maxiter = 1000):
		p = 2 * x - 1
		i = 0
		conctr = 0
		eold = sys.maxint
		while i < maxiter:
			k = np.random.randint(0, self.dim)
			net = np.dot(self.w[k], p)
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
			if conctr > 300:
					break
			#time.sleep(0.1)
			print("%s   |   %d \r" % (BtoC((p > 0).astype(int)), e))
			#sys.stdout.write("%s   |   %d \r" % (BtoC((p > 0).astype(int)), e))
			#sys.stdout.flush()
		return (p > 0).astype(int)


def CtoB(myString):
	if len(myString) < 10:
		i = 10 - len(myString)
		k = 0
		while (k < i):
			myString += '\0'
			k += 1
			
	result = []
	for ch in myString:
		bits = (bin(ord(ch))[2:])
		bits = '00000000'[len(bits):] + bits
		result.extend([int(b) for b in bits])
	return result
	
def BtoC(myString):
    chars = []
    for b in range(len(myString) / 8):
        byte = myString[b*8:(b+1)*8]
        chars.append(chr(int(''.join([str(bit) for bit in byte]), 2)))
    return ''.join(chars)		


if __name__ == '__main__':
	dim = 80
	hopnet = HopfieldNetwork(dim)
	
	#first set of inputs
	while True:
		inString = raw_input("Enter some 10 character strings : ")
		if inString == 'q' or inString == 'Q': break
		inString = inString[:10]
		binString = np.zeros(0)
		
		for c in CtoB(inString):
			binString = np.append(binString, c)

		hopnet.update(binString)        
		
		#next set of inputs
	while True:
		inString = raw_input("Enter a new 10 character string : ")		
		inString = inString[:10]
		if inString == 'q' or inString == 'Q': break		
		binString = np.zeros(0)
		
		for c in CtoB(inString):
			binString = np.append(binString, c)

		print(BtoC(hopnet.run(binString)))		
