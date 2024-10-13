from flask import redirect, render_template, request, jsonify, session
from flask import Flask
import spacy
from googletrans import Translator
from flask_sqlalchemy import SQLAlchemy
import py_eureka_client.eureka_client as eureka_client

app = Flask(__name__)
app.config.from_object('config.DevelopmentConfig')
db = SQLAlchemy(app)

# using NLP model
nlp = spacy.load('en_core_web_sm')

translator = Translator()

EUREKA_SERVER_URL = "http://35.243.98.26:8761/eureka/"

def register_with_eureka():
    eureka_client.init(
        eureka_server=EUREKA_SERVER_URL,
        app_name="blog-analyze-service",
        instance_port=5000,
        instance_id="blog-a-service-1",
        instance_ip="http://35.243.98.26",
        instance_host="35.243.98.26"
    )


with app.app_context():
    register_with_eureka()


def filter_words(text):
    doc = nlp(text)

    nouns = [token.text for token in doc if token.pos_ == "NOUN"]
    verbs = [token.text for token in doc if token.pos_ == "VERB"]
    adjectives = [token.text for token in doc if token.pos_ == "ADJ"]

    return {
        "nouns": nouns,
        "verbs": verbs,
        "adjectives": adjectives
    }


def translate_words(words, dest_lang="vi"):
    translated_words = [translator.translate(word, dest=dest_lang).text for word in words]
    return translated_words


@app.route('/analyze-text', methods=['POST'])
def analyze_text():
    data = request.json
    text = data.get('text', '')

    if not text:
        return jsonify({"error": "No text provided"}), 400

    result = filter_words(text)

    return jsonify(result)


@app.route("/translate", methods=['POST'])
def translate_word():
    data = request.json
    word = data.get('word', '')
    target_lang = data.get('lang', 'vi')

    if not word:
        return jsonify({'error': 'Word is required'}), 400

    try:
        translated = translator.translate(word, dest=target_lang)
        return jsonify({
            'original': word,
            'translated': translated.text,
            'lang': target_lang
        })
    except Exception as e:
        return jsonify({'error': str(e)}), 500


if __name__ == "__main__":
    app.run(debug=True)
