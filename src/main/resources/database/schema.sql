-- this will drop tables if already available (prevents error on re-run)
DROP TABLE IF EXISTS transaction_history;
DROP TABLE IF EXISTS book_collection;
DROP TABLE IF EXISTS users;


-- users table (id changed to string form)
CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    password VARCHAR(64) NOT NULL,
    fine INTEGER DEFAULT 0,
    borrowed_books INTEGER DEFAULT 0,
    last_login_time TIMESTAMP

);

--  book_collection table (id changed to string form)
CREATE TABLE book_collection (
    id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    author VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    total_copies INTEGER NOT NULL CHECK (total_copies > 0),
    availability BOOLEAN DEFAULT TRUE
);

--  Create transaction_history table
CREATE TABLE transaction_history (

    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL REFERENCES users(id),
    book_name VARCHAR(50) NOT NULL REFERENCES book_collection(title)
);


-- sample books (20+ entries with at least 5 copies, 1 library copy)
INSERT INTO book_collection (id, title, author, genre, total_copies) VALUES
-- Dan Brown Books
('B001', 'Angels & Demons', 'Dan Brown', 'Thriller', 6),
('B002', 'The Da Vinci Code', 'Dan Brown', 'Thriller', 6),
('B003', 'The Lost Symbol', 'Dan Brown', 'Thriller', 6),
('B004', 'Inferno', 'Dan Brown', 'Thriller', 6),
('B005', 'Origin', 'Dan Brown', 'Thriller', 6),
('B006', 'Digital Fortress', 'Dan Brown', 'Tech Thriller', 6),
('B007', 'Deception Point', 'Dan Brown', 'Tech Thriller', 6),

-- Harry Potter Series
('B008', 'Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 'Fantasy', 6),
('B009', 'Harry Potter and the Chamber of Secrets', 'J.K. Rowling', 'Fantasy', 6),
('B010', 'Harry Potter and the Prisoner of Azkaban', 'J.K. Rowling', 'Fantasy', 6),
('B011', 'Harry Potter and the Goblet of Fire', 'J.K. Rowling', 'Fantasy', 6),
('B012', 'Harry Potter and the Order of the Phoenix', 'J.K. Rowling', 'Fantasy', 6),
('B013', 'Harry Potter and the Half-Blood Prince', 'J.K. Rowling', 'Fantasy', 6),
('B014', 'Harry Potter and the Deathly Hallows', 'J.K. Rowling', 'Fantasy', 6),

-- Paulo Coelho Books
('B015', 'The Alchemist', 'Paulo Coelho', 'Philosophical', 6),
('B016', 'Brida', 'Paulo Coelho', 'Spiritual', 6),
('B017', 'Veronika Decides to Die', 'Paulo Coelho', 'Drama', 6),

-- Other Genres
('B018', 'The Subtle Art of Not Giving a F*ck', 'Mark Manson', 'Self-help', 6),
('B019', 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 'History', 6),
('B020', 'A Brief History of Time', 'Stephen Hawking', 'Science', 6),
('B021', 'Atomic Habits', 'James Clear', 'Self-help', 6),
('B022', 'To Kill a Mockingbird', 'Harper Lee', 'Fiction', 6);

