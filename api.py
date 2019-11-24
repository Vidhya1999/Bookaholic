from flask import Flask, request
from book import books
import json
import os
os.chdir("/home/vidhya/Project_final")
import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer, CountVectorizer
from sklearn.metrics.pairwise import linear_kernel, cosine_similarity
from scipy.sparse import csr_matrix

app = Flask(__name__)

@app.route("/book", methods=['GET', 'POST'])
def hello() :
        args1 = ""
        for i in request.args.get('args') :
                args1 = args1+i
        args1 = books(args1);        
        return args1

                
if __name__ == "__main__" :
        app.run(port=8080)
