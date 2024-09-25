import os

import psycopg2
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
                            password="8CaA9ws1iWZydsR4AhNFAWzIB3cRtgIO",
                            host=os.getenv('DB_HOST'),
                            port=os.getenv('DB_PORT'),
                            sslmode='require'
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

    recommendations = coll + content
    recommendations = list(set(recommendations))
    #
    # recommendations = pd.concat([coll, content], axis=0).drop_duplicates()
    # return jsonify(recommendations)
    return jsonify(recommendations)


# if __name__ == '__main__':
#     app.run(debug=True)
