package ru.kolya.rfidserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kolya.rfidserver.dto.PassDto;
import ru.kolya.rfidserver.model.Pass;
import ru.kolya.rfidserver.model.User;
import ru.kolya.rfidserver.repository.PassRepository;
import ru.kolya.rfidserver.repository.UserRepository;

@RestController
@RequestMapping("/passes")
public class PassController {
    @Autowired
    private PassRepository passRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Pass> registerPass(PassDto passDto) {
        User user = userRepository.findByCardNumber(passDto.getUserCardNumber());
        Pass lastPass = passRepository.findLastByUser(user);

        if (lastPass != null && lastPass.getCome() == null) {
            lastPass.setCome(passDto.getTime());
        } else if (lastPass != null && lastPass.getGone() == null) {
            lastPass.setGone(passDto.getTime());
        } else {
            lastPass = new Pass(user, passDto.getTime());
        }

        return ResponseEntity.ok(passRepository.save(lastPass));
    }
}
