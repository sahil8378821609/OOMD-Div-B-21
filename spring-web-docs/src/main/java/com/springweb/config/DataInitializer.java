package com.springweb.config;

import com.springweb.entity.User;
import com.springweb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

       private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
       private final UserRepository userRepository;
       private static boolean initialized = false;

       public DataInitializer(UserRepository userRepository) {
              this.userRepository = userRepository;
       }

       @Override
       @Transactional
       public void run(String... args) throws Exception {
              // Prevent multiple executions
              if (initialized) {
                     logger.info("DataInitializer already executed, skipping...");
                     return;
              }

              // Check if users already exist to avoid duplicates
              long userCount = userRepository.count();
              if (userCount == 0) {
                     logger.info("Initializing default users...");
                     createDefaultUsers();
                     initialized = true;
              } else {
                     logger.info("Users already exist ({}), skipping initialization", userCount);
                     initialized = true;
              }
       }

       private void createDefaultUsers() {
              // Create default users with their information, bio and social media
              User[] defaultUsers = {
                            new User("SRIE_VI", "09", "ស្រ៊ី វី", "male", "userP/vi.jpg",
                                          "អ្នកចូលចិត្តបច្ចេកវិទ្យា។",
                                          "https://facebook.com/me", "https://instagram.com/srievii",
                                          "https://x.com", "https://github.com/EIRSVi/SpringWeb",
                                          "https://youtube.com/?"),

                            new User("EAM_VIMORL", "13", "អៀម វិមល។", "male", "userP/vimorl.jpg",
                                          "អ្នកអភិវឌ្ឍន៍កម្មវិធី។",
                                          "https://facebook.com/me", "https://instagram.com/eamvimorl",
                                          "https://x.com", "https://github.com/EIRSVi/SpringWeb",
                                          "https://youtube.com/?"),
                            new User("PHAL_KHAMLA", "09", "ផល ខាំឡា", "female", "userP/kamla.jpg",
                                          "អ្នករចនាប្រព័ន្ធ UI/UX។",
                                          "https://facebook.com/me", "https://instagram.com/phalkhamla",
                                          "https://x.com", "https://github.com/EIRSVi/SpringWeb",
                                          "https://youtube.com/?"),
                            new User("LONG_SREYNET", "04", "ឡុង ស្រីណេត", "female", "userP/sreynet.jpg",
                                          "អ្នកវិភាគទិន្នន័យ។",
                                          "https://facebook.com/me", "https://instagram.com/longsreynet",
                                          "https://x.com", "https://github.com/EIRSVi/SpringWeb",
                                          "https://youtube.com/?"),
                            new User("PHEAT_PISEY", "24", "ភាត់ ពីសី", "female", "userP/pisey.jpg",
                                          "អ្នកគ្រប់គ្រងគម្រោង។",
                                          "https://facebook.com/me", "https://instagram.com/pheatpisey",
                                          "https://x.com", "https://github.com/EIRSVi/SpringWeb",
                                          "https://youtube.com/?"),
                            new User("ROEURN_MAKARA", "21", "រឿន មករា", "female", "userP/makara.jpg",
                                          "អ្នកធានាគុណភាពកម្មវិធី។",
                                          "https://facebook.com/me", "https://instagram.com/roeurnmakara",
                                          "https://x.com", "https://github.com/EIRSVi/SpringWeb",
                                          "https://youtube.com/?")
              };

              try {
                     for (User user : defaultUsers) {
                            userRepository.save(user);
                            logger.info("Created user: {} with username: {}", user.getName(), user.getUsername());
                     }
                     logger.info("Default users initialized successfully!");
              } catch (Exception e) {
                     logger.error("Error creating default users: ", e);
              }
       }
}