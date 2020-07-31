package com.wallet.service.controller

import com.wallet.service.dto.TransferDto
import com.wallet.service.dto.TransferRequest
import com.wallet.service.service.TransferService
import org.springframework.data.domain.PageRequest
import org.springframework.scheduling.annotation.Async
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/api/transfer")
class TransferController(private val transferService: TransferService) {

    @Async
    @PostMapping
    fun post(@RequestBody @Valid dto: TransferRequest): TransferDto {
        return transferService.transferCash(dto)
    }

    @GetMapping
    fun list(@RequestParam(defaultValue = "0") page: Int,
             @RequestParam(defaultValue = "20") size: Int): List<TransferDto> {
        return transferService.getAll(PageRequest.of(page, size))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): TransferDto {
        return transferService.getById(id)
    }
}
