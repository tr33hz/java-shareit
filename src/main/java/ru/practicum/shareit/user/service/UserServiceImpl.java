package ru.practicum.shareit.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.practicum.shareit.exception.DataExistException;
import ru.practicum.shareit.exception.UserOrItemNotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.cfg.AvailableSettings.USER;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userMapper.convertFromDto(userDto);
        User userSaved = userRepository.save(user);
        return userMapper.convertToDto(userSaved);
    }

    @Transactional
    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        User user = userMapper.convertFromDto(userDto);
        User targetUser = userRepository.findById(id).orElseThrow(() -> new DataExistException(USER, id));
        if (StringUtils.hasLength(user.getEmail())) {
            targetUser.setEmail(user.getEmail());
        }
        if (StringUtils.hasLength(user.getName())) {
            targetUser.setName(user.getName());
        }
        User userSaved = userRepository.save(targetUser);
        return userMapper.convertToDto(userSaved);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserOrItemNotFoundException(String.format("Пользователь с id %s не найден", userId)));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserOrItemNotFoundException(String.format("Пользователь с id %s не найден", userId)));
        return userMapper.convertToDto(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeUser(long id) {
        userRepository.deleteById(id);
    }
}