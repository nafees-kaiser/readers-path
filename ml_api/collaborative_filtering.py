import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity


def collaborative_filtering(df, title, num_of_books):

    # books2 = pd.DataFrame(books2, columns=['id', 'isbn', 'title', 'app_user_id', 'rating'])

    # df = pd.DataFrame(df)
    # x = df.groupby('app_user_id').count()['rating'] > 200
    # reviewing_users_indices = x[x].index
    # filtered_books = df[df['app_user_id'].isin(reviewing_users_indices)]

    df['rating'] = df['rating'].astype(float)

    y = df.groupby('title').count()['rating'] >= 50
    reviewed_books_indices = y[y].index
    final_ratings = df[df['title'].isin(reviewed_books_indices)]

    pt = final_ratings.pivot_table(index='title', columns='app_user_id', values='rating')
    pt.fillna(0, inplace=True)

    sm = cosine_similarity(pt)

    idx = np.where(pt.index == title)[0][0]
    similar_books_tuples = sorted(list(enumerate(sm[idx])), key=lambda i: i[1], reverse=True)[1:num_of_books+1]
    similar_books_indices = [i for i, scr in similar_books_tuples]
    similar_books = df.iloc[similar_books_indices]
    # print(similar_books['id'].tolist())
    return similar_books['id'].tolist()

