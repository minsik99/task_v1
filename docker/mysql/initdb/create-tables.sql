USE `firstTask`;

-- 제품 테이블
CREATE TABLE IF NOT EXISTS `Product` (
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `reviewCount` BIGINT(20) NOT NULL,
    `score`       FLOAT  NOT NULL
) ENGINE = InnoDB CHARSET = utf8;

-- 리뷰 테이블
CREATE TABLE IF NOT EXISTS `Review` (
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `productId`   BIGINT(20) NOT NULL,
    `userId`      TINYINT NOT NULL,
    `content`     TEXT NOT NULL,
    `imageUrl`    VARCHAR(255),
    `createdAt`   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `fk_review_product` FOREIGN KEY (`productId`) REFERENCES `Product` (`id`) ON DELETE CASCADE,
    UNIQUE KEY `uk_user_product` (`userId`, `productId`)
) ENGINE = InnoDB CHARSET = utf8;

-- 가장 최근에 작성된 리뷰 순으로 정렬된 리뷰 조회를 위한 인덱스
CREATE INDEX `idx_review_createdAt` ON `Review` (`productId`, `createdAt`);
