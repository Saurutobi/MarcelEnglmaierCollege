import numpy as np
import cPickle, random
import time


class Perceptron(object):
	def __init__(self, idim):
		self.idim = idim
		# set of 10 weights
		self.weights = [np.zeros(idim)] * 10
		# set of 10 biases
		self.bias = [0] * 10

	def predict(self, x):
		highestFuncVal = None
		yHat = None
		for i in range(10):
			funcVal = np.dot(self.weights[i], x) + self.bias[i]
			if funcVal > highestFuncVal or funcVal == None:
				highestFuncVal = funcVal
				yHat = i
		return yHat

	def update(self, x, y):
		p = self.predict(x)
		if p != y:
			# adjust weights and bias if the prediction was not correct
			for w in range(self.idim):
				self.weights[p][w] = self.weights[p][w] - x[w]
				self.weights[y][w] = self.weights[y][w] + x[w]
			self.bias[p] = self.bias[p] - 1
			self.bias[y] = self.bias[y] + 1

if __name__ == '__main__':
	# read in the data and 'unpickle' it.  lol.
	with open('./littlemnist.pkl', 'rb') as f:
		(trainX, trainY), (validX, validY), (testX, testY) = cPickle.load(f)
	# training data
	D = [(x, y) for x, y in zip(trainX, trainY)]
	# test data
	V = [(x, y) for x, y in zip(validX, validY)]

	# make a perceptron, but its a multitron.
	clf = Perceptron(len(trainX[0]))
	
	for x, y in D:
		x = np.reshape(x, (28, 28))
		
		for i in range(28):
			rAvg = np.sum(x[i]) / 28
			for j in range(28):
				if i > 0 and i < 27 and j > 0 and j < 27:
					gAvg = (x[i][j-1])+ (x[i-1][j]) + (x[i][j+1]) + (x[i+1][j]) /4
					if x[i][j] > gAvg:
						x[i][j] += gAvg
		
		x = np.reshape(x, 784)
	

	while True:
		random.shuffle(D)
		# train
		for x, y in D: 
			clf.update(x, y)

		# test
		error = 0
		for x, y in V:
			error += int(y != clf.predict(x))
		print error/float(len(V))*100
		
