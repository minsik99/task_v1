-- 테스트를 위해 Product 테이블에 데이터 하나 추가
INSERT INTO Product (reviewCount, score)
VALUES (15, 3.4);

-- 테스트를 위해 Review 테이블에 데이터 추가
INSERT INTO Review (productId, userId, score, content, imageUrl, createdAt)
VALUES (1, 1, 4, '좋아요.', 'https://amazon-s3-url.com/sample1.png', NOW()),
       (1, 2, 5, '매우 좋습니다.', 'https://amazon-s3-url.com/sample2.png', NOW()),
       (1, 3, 3, '보통이에요.', NULL, NOW()),
       (1, 4, 2, '별로입니다.', 'https://amazon-s3-url.com/sample3.png', NOW()),
       (1, 5, 4, '괜찮네요.', 'https://amazon-s3-url.com/sample4.png', NOW()),
       (1, 6, 5, '완벽합니다!', 'https://amazon-s3-url.com/sample5.png', NOW()),
       (1, 7, 1, '최악입니다.', NULL, NOW()),
       (1, 8, 4, '좋아요.', 'https://amazon-s3-url.com/sample6.png', NOW()),
       (1, 9, 3, '보통이에요.', NULL, NOW()),
       (1, 10, 5, '강추합니다.', 'https://amazon-s3-url.com/sample7.png', NOW()),
       (1, 11, 2, '다시는 안 살 거예요.', NULL, NOW()),
       (1, 12, 3, '그럭저럭입니다.', NULL, NOW()),
       (1, 13, 4, '기대 이상입니다.', 'https://amazon-s3-url.com/sample8.png', NOW()),
       (1, 14, 5, '최고예요!', NULL, NOW()),
       (1, 15, 1, '별로예요.', 'https://amazon-s3-url.com/sample9.png', NOW());