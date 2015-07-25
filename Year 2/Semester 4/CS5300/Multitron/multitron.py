#Author: Marcel Englmaier
import numpy as np
import cPickle, random

class Perceptron(object):
	#initialize Perceptron as a Multitron
	def __init__(self, idim):
		self.idim = idim
		# set of 10 weights
		self.weights = [np.zeros(idim) for x in range(10)]
		# set of 10 biases
		self.bias = [0 for x in range(10)]

	#make a prediction
	def predict(self, x):
		highestFuncVal = None
		yHat = None
		for i in range(10):
			funcVal = np.dot(self.weights[i], x) + self.bias[i]
			if funcVal > highestFuncVal or funcVal == None:
				highestFuncVal = funcVal
				yHat = i
		return yHat

	#update weights
	def update(self, x, y):
		p = self.predict(x)
		if p != y:
			# adjust weights and bias if the prediction was not correct
			for w in range(self.idim):
				self.weights[p][w] = self.weights[p][w] - x[w]
				self.weights[y][w] = self.weights[y][w] + x[w]
			self.bias[p] = self.bias[p] - biassub
			self.bias[y] = self.bias[y] + biasadd

if __name__ == '__main__':
	# read in the data and 'unpickle' it.  lol.
	with open('./littlemnist.pkl', 'rb') as f:
		(trainX, trainY), (validX, validY), (testX, testY) = cPickle.load(f)
	# training data
	D = [(x, y) for x, y in zip(trainX, trainY)]
	# Valid data
	V = [(x, y) for x, y in zip(validX, validY)]
	# test data
	T = [(x, y) for x, y in zip(testX, testY)]

	# make a perceptron, but its a multitron.
	clf = Perceptron(len(trainX[0]))
	
	biasadd = 1
	biassub = 1
	
	while True:
		avg = 0
		random.shuffle(D)
		# train
		for x, y in D: 
			clf.update(x, y)
		# Validate
		error = 0
		for x, y in V:
			error += int(y != clf.predict(x))
		#print error / float(len(V)), len(V)

		# test
		error = 0
		for x, y in T:
			error += int(y != clf.predict(x))
		printer = error/float(len(V))
		print printer, len(V)
		
