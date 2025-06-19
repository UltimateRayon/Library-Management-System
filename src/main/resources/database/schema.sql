-- this will drop tables if already available (prevents error on re-run)
DROP TABLE IF EXISTS transaction_history;
DROP TABLE IF EXISTS book_collection;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- 2. Create book_collection table
CREATE TABLE book_collection (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    total_copies INTEGER NOT NULL CHECK (total_copies > 0),
    availability BOOLEAN DEFAULT TRUE
);


CREATE TABLE transaction_history (
    transaction_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users(id),
    book_id INTEGER NOT NULL REFERENCES book_collection(id),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- sample books (20+ entries with at least 5 copies, 1 library copy)
INSERT INTO book_collection (title, author, genre, total_copies) VALUES
-- Dan Brown Books
('Angels & Demons', 'Dan Brown', 'Thriller', 6),
('The Da Vinci Code', 'Dan Brown', 'Thriller', 6),
('The Lost Symbol', 'Dan Brown', 'Thriller', 6),
('Inferno', 'Dan Brown', 'Thriller', 6),
('Origin', 'Dan Brown', 'Thriller', 6),
('Digital Fortress', 'Dan Brown', 'Tech Thriller', 6),
('Deception Point', 'Dan Brown', 'Tech Thriller', 6),

-- Harry Potter Series
('Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 'Fantasy', 6),
('Harry Potter and the Chamber of Secrets', 'J.K. Rowling', 'Fantasy', 6),
('Harry Potter and the Prisoner of Azkaban', 'J.K. Rowling', 'Fantasy', 6),
('Harry Potter and the Goblet of Fire', 'J.K. Rowling', 'Fantasy', 6),
('Harry Potter and the Order of the Phoenix', 'J.K. Rowling', 'Fantasy', 6),
('Harry Potter and the Half-Blood Prince', 'J.K. Rowling', 'Fantasy', 6),
('Harry Potter and the Deathly Hallows', 'J.K. Rowling', 'Fantasy', 6),

-- Paulo Coelho Books
('The Alchemist', 'Paulo Coelho', 'Philosophical', 6),
('Brida', 'Paulo Coelho', 'Spiritual', 6),
('Veronika Decides to Die', 'Paulo Coelho', 'Drama', 6),

-- Other Genres
('The Subtle Art of Not Giving a F*ck', 'Mark Manson', 'Self-help', 6),
('Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 'History', 6),
('A Brief History of Time', 'Stephen Hawking', 'Science', 6),
('Atomic Habits', 'James Clear', 'Self-help', 6),
('To Kill a Mockingbird', 'Harper Lee', 'Fiction', 6);
