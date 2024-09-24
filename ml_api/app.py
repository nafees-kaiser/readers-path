import os
import random

import psycopg2
import numpy as np
import requests
from faker import Faker
from dotenv import load_dotenv
from flask import Flask, request, jsonify

from collaborative_filtering import collaborative_filtering
from content_based_filtering import content_based_filtering
import pandas as pd

load_dotenv()

app = Flask(__name__)


def get_db_connection():
    conn = psycopg2.connect(database=os.getenv('DB'),
                            user=os.getenv('USER_NAME'),
                            password=os.getenv('PWD'),
                            host=os.getenv('HOST'),
                            port=os.getenv('PORT')
                            )
    cur = conn.cursor()
    return conn, cur


def execute_query(query):
    conn, cur = get_db_connection()
    cur.execute(query)
    result = cur.fetchall()
    conn.commit()
    cur.close()
    conn.close()
    return result


@app.route("/add-db", methods=["GET"])
def add_db():
    # df_books = pd.read_csv('data/Books.csv')
    # df_ratings = pd.read_csv('data/Ratings.csv')
    #
    # # print(df_books.head(20))
    # # books_20 = df_books.head(20)
    #
    conn, cur = get_db_connection()
    #
    # cur.execute("SELECT isbn FROM BOOK")
    # isbn_list = [row[0] for row in cur.fetchall()]
    #
    # filtered_users = df_ratings[df_ratings["ISBN"].isin(isbn_list)]
    # unique_user_ids = filtered_users['User-ID'].nunique()
    # print(unique_user_ids)
    #
    # # Commit the transaction and close the connection
    # conn.commit()
    # cur.close()
    # conn.close()

    # add_user()

    # update_rating()

    return jsonify({'msg': "i hope my pc did not crash"})


def update_rating():
    fake = Faker()
    endp = "http://localhost:8080/api/v1/user/add-review"

    conn, cur = get_db_connection()

    book_ids = list(range(41, 61))
    ratings_distribution = np.random.choice([1, 2, 3, 4, 5], size=50, p=[0.2, 0.2, 0.2, 0.2, 0.2])

    user_ids = list(range(1, 51))

    for i, user_id in enumerate(user_ids):
        cur.execute("select email from app_user where id = %s", (user_id,))
        email = cur.fetchone()[0]

        for j in book_ids:
            review = fake.sentence(nb_words=10)

            rating = ratings_distribution[i]

            book_id = j

            review_data = {
                "review": review,
                "rating": str(rating),
                "book": {
                    "id": book_id
                }
            }
            response = requests.post(f"{endp}/{email}", json=review_data)


@app.route('/ml/get-recommendations', methods=['POST'])
def get_recommendations():
    sql = """
    select id, title, isbn, edition, publisher, page_count from book
    """
    books = execute_query(sql)
    books = pd.DataFrame(books, columns=['id', 'title', 'isbn', 'edition', 'publisher', 'page_count'])
    # print(books)
    title = request.json['title']
    num_of_books = request.json['num_of_books']

    sql = """
    select b.id, isbn, title, app_user_id, rating
    from reviews_and_rating rr
    inner join app_user au on rr.app_user_id = au.id 
    inner join book b on rr.book_id = b.id
    """

    books2 = execute_query(sql)
    books2 = pd.DataFrame(books2, columns=['id', 'isbn', 'title', 'app_user_id', 'rating'])
    # print(books2)

    coll = collaborative_filtering(books2, title, num_of_books)
    content = content_based_filtering(books, title, num_of_books)
    #
    # recommendations = pd.concat([coll, content], axis=0).drop_duplicates()
    # return jsonify(recommendations)
    return jsonify(books2)


def add_user():
    fake = Faker()

    endp = "http://localhost:8080/api/v1/add-user"

    for _ in range(70):
        name = fake.name()
        email = fake.email()
        password = "1234"

        user_data = {
            "name": name,
            "email": email,
            "password": password,
        }

        res = requests.post(endp, json=user_data)


if __name__ == '__main__':
    app.run(debug=True)
