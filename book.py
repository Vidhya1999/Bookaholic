import os
os.chdir("/home/vidhya/Project_final")
import numpy as np
import pickle
#import matplotlib.pyplot as plt
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer, CountVectorizer
from sklearn.metrics.pairwise import linear_kernel, cosine_similarity
from scipy.sparse import csr_matrix
import json

def books(s):
	stringedTags=pd.read_csv('/home/vidhya/Documents/book/Booktags.csv')
	countVec = CountVectorizer(analyzer = 'word', ngram_range = (1, 2), min_df = 0, stop_words = 'english')
	tagMatrix = countVec.fit_transform(stringedTags['all_tags'])
	cosineSim = cosine_similarity(tagMatrix, tagMatrix)
	stringedTags = stringedTags.reset_index()
	bookTitles = stringedTags['title']
	indices = pd.Series(stringedTags.index, index = bookTitles)
	def topRecommendations(title):
	    index = indices[title]
	    similarityScore = list(enumerate(cosineSim[index]))
	    similarityScore = sorted(similarityScore, key = lambda x: x[1], reverse = True)
	    similarityScore = similarityScore[1:10]
	    bookIndex = [i[0] for i in similarityScore]
	    return bookTitles.iloc[bookIndex]

	TR=topRecommendations(s).head(10)
	TR_dict=TR.to_dict()
	python2json = json.dumps(TR_dict)
	print python2json
	return python2json
