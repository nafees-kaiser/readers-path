import numpy as np
import pandas as pd
import re
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.neighbors import NearestNeighbors


def preprocess_title(title):
    title = title.lower()
    title = re.sub(r'\W+', ' ', title)
    return title.strip()


def content_based_filtering(df, title, num_of_books):
    df['processed_title'] = df['title'].apply(preprocess_title)

    tfidf = TfidfVectorizer(stop_words='english')
    tfidf_matrix = tfidf.fit_transform(df['processed_title'])

    knn = NearestNeighbors(metric='cosine', n_neighbors=num_of_books)
    knn.fit(tfidf_matrix)

    title = preprocess_title(title)
    idx = np.where(df['processed_title'] == title)[0][0]
    # test_idf = tfidf.transform([title])
    dist, indices = knn.kneighbors(tfidf_matrix[idx], n_neighbors=num_of_books + 1)
    # print(indices)
    titles = df.iloc[indices[0][1:]]
    # print(titles)
    return titles

