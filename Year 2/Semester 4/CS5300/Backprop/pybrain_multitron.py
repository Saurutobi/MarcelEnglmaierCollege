#Author: Marcel Englmaier
import cPickle

from pybrain.datasets import ClassificationDataSet
from pybrain.tools.shortcuts import buildNetwork

from pybrain.supervised.trainers import BackpropTrainer
from pybrain.structure.modules import SoftmaxLayer
from pybrain.utilities import percentError
from pybrain.tools.neuralnets import NNregression, Trainer, NNclassifier

#import sys
if __name__ == '__main__': 
    with open('./littlemnist.pkl', 'rb') as f:
        (trainX, trainY), (validX, validY), (testX, testY) = cPickle.load(f)    
    
    trainingSet = ClassificationDataSet(28 * 28, nb_classes=10)
    for image, label in zip(trainX, trainY):
        trainingSet.addSample(image, label)

    testingSet = ClassificationDataSet(28 * 28, nb_classes=10)
    for image, label in zip(validX, validY):
        testingSet.addSample(image, label)

    trainingSet._convertToOneOfMany()
    testingSet._convertToOneOfMany()
    
    fnn = buildNetwork(trainingSet.indim, 100, trainingSet.outdim, outclass=SoftmaxLayer, bias=True)
    trainer = BackpropTrainer(fnn, dataset=trainingSet, momentum=0.05, weightdecay=0.01)
    
    for i in range(20):
        trainer.trainEpochs(1)
        trn_result = percentError(trainer.testOnClassData(), trainingSet['class'])
        tst_result = percentError(trainer.testOnClassData(dataset=testingSet), testingSet['class'])

        print "epoch: %3d  | " % trainer.totalepochs, \
              "train error: %5.2f%%  | " % trn_result, \
              "test error: %5.2f%%" % tst_result 
