-- Insert sample data for tutorials
INSERT INTO tutorials (title, description, published) VALUES
  ('Spring Boot Basics', 'Introduction to Spring Boot framework', true),
  ('Swagger Integration', 'Using Swagger for API documentation', false),
  ('Spring Security', 'Securing Spring applications', true),
  ('Docker for Java Apps', 'Deploying Java applications with Docker', false);

  
  
CREATE TABLE tutorials (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  published BOOLEAN NOT NULL
);
