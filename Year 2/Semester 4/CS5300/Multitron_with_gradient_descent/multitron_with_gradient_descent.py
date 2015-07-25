#Author: Marcel Englmaier
import sys
import numpy as np
import cPickle, random 

class Perceptron(object):
	#initialize Perceptron
	def __init__(self, idim):
		self.idim = idim
		self.w = np.zeros((10, idim))
		self.b = np.zeros((10))
	
	#make a prediction
	def predict(self, x):
		return np.argmax(np.dot(self.w, x) + self.b)
	
	#update the net
	def update(self, x, y):
		p = self.predict(x)
		if p != y:
			self.w[p] -= x
			self.w[y] += x
			self.b[p] -= Bias
			self.b[y] += Bias

if __name__ == '__main__':
	with open('./littlemnist.pkl', 'rb') as f:
		(trainX, trainY), (validX, validY), (testX, testY) = cPickle.load(f)

	D = zip(trainX, trainY)
	V = zip(validX, validY)

	#train
	clf = Perceptron(len(trainX[0]))

	sum_ = 0
	i = 0
	lastError = 0
	biasOffset = .01
	Bias = 1
	while True:
		random.shuffle(D)

		#update
		for x, y in D:
			clf.update(x, y)
		#test
		error = 0
		for x, y in V:
			error += int(y != clf.predict(x))

		#change bias to attempt Gradient Descent
		i += 1
		e = error/float(len(V))
		if e > lastError:
			Bias = Bias + biasOffset
		if e == lastError:
			Bias = Bias - biasOffset
		lastError = e
		sum_ += e
		print "error=", '{0:0.02f}'.format(e*100), "Avg Error=", '{0:0.02f}'.format((sum_/i)*100), "Bias=", '{0:0.02f}'.format(Bias)
		#print >> sys.stderr, "error = ", '{0:0.02f}'.format(e), "\r",
