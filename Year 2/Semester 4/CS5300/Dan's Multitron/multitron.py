# Multitron -- identifying characters from MNIST data
import sys
"""
SCIENCE!
   There wasn't too much experimentation to be done with this assignment.
   
"""
import numpy as np
import cPickle, random 

class Perceptron(object):
   def __init__(self, idim):
      self.idim = idim
      self.w = np.zeros((10, idim))
      self.b = np.zeros((10))

   def predict(self, x):
      return np.argmax(np.dot(self.w, x) + self.b)

   def update(self, x, y):
      p = self.predict(x)
      if p != y:
         self.w[p] -= x
         self.w[y] += x
         self.b[p] -= 1
         self.b[y] += 1

if __name__ == '__main__':
   with open('./littlemnist.pkl', 'rb') as f:
      (trainX, trainY), (validX, validY), (testX, testY) = cPickle.load(f)

   D = zip(trainX, trainY)
   V = zip(validX, validY)

   clf = Perceptron(len(trainX[0]))

   sum_ = 0
   i = 0
   while True:
      random.shuffle(D)

      for x, y in D:
         clf.update(x, y)
      
      error = 0
      for x, y in V:
         error += int(y != clf.predict(x))
       
      i += 1
      e = error/float(len(V))
      sum_ += e
      #print >> sys.stderr, "error = %.2f%% -- average = %.2f%%\r" % (e, sum_/i),
      print >> sys.stderr, "error = ", '{0:0.02f}'.format(e), "\r",
