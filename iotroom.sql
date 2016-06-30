-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 30, 2016 at 04:16 AM
-- Server version: 10.0.17-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `iotroom`
--

-- --------------------------------------------------------

--
-- Table structure for table `systems`
--

CREATE TABLE `systems` (
  `id` int(11) NOT NULL,
  `ac_mode` tinyint(1) NOT NULL DEFAULT '0',
  `ac1` tinyint(1) NOT NULL DEFAULT '1',
  `ac2` tinyint(1) NOT NULL DEFAULT '1',
  `ac3` tinyint(1) NOT NULL DEFAULT '0',
  `ac4` tinyint(1) NOT NULL DEFAULT '0',
  `door_state` tinyint(1) NOT NULL DEFAULT '0',
  `current_temp` double NOT NULL DEFAULT '0',
  `user_temp` double NOT NULL DEFAULT '0',
  `system_hash` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `systems`
--

INSERT INTO `systems` (`id`, `ac_mode`, `ac1`, `ac2`, `ac3`, `ac4`, `door_state`, `current_temp`, `user_temp`, `system_hash`, `created_at`, `updated_at`) VALUES
(1, 0, 1, 1, 0, 0, 0, 0, 0, 'aa12b', '2016-06-30 01:07:39', '2016-06-30 01:07:39'),
(2, 0, 1, 1, 0, 0, 0, 0, 0, 'cc32w', '2016-06-30 01:07:39', '2016-06-30 01:09:27'),
(3, 0, 1, 1, 0, 0, 0, 0, 0, 'dd44r', '2016-06-30 01:07:39', '2016-06-30 01:07:39'),
(4, 0, 1, 1, 0, 0, 0, 0, 0, 'kk62h', '2016-06-30 01:07:39', '2016-06-30 01:07:39'),
(5, 0, 1, 1, 0, 0, 0, 0, 0, 'zz81t', '2016-06-30 01:07:39', '2016-06-30 01:07:39');

-- --------------------------------------------------------

--
-- Table structure for table `temp_log`
--

CREATE TABLE `temp_log` (
  `id` int(11) NOT NULL,
  `temp` double NOT NULL,
  `system_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role` int(11) NOT NULL DEFAULT '2',
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `system_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`, `token`, `system_id`) VALUES
(1, 'eslam', '12345', 1, 'kkkkkkk', 2),
(2, 'mido', '12345', 2, 'nsdf', 2),
(3, 'jekso', '12345', 2, 'jdjdje', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `systems`
--
ALTER TABLE `systems`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `system_hash` (`system_hash`);

--
-- Indexes for table `temp_log`
--
ALTER TABLE `temp_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `system_id` (`system_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `token` (`token`),
  ADD KEY `system_id` (`system_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `systems`
--
ALTER TABLE `systems`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `temp_log`
--
ALTER TABLE `temp_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `temp_log`
--
ALTER TABLE `temp_log`
  ADD CONSTRAINT `temp_log_ibfk_1` FOREIGN KEY (`system_id`) REFERENCES `systems` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`system_id`) REFERENCES `systems` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
