package com.dio.bootcamp.service;

import com.dio.bootcamp.exceptions.BusinessException;
import com.dio.bootcamp.exceptions.NotFoundException;
import com.dio.bootcamp.mapper.StockMapper;
import com.dio.bootcamp.model.Stock;
import com.dio.bootcamp.model.dto.StockDTO;
import com.dio.bootcamp.repository.StockRepository;
import com.dio.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {


    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Transactional
    public StockDTO save(StockDTO dto) {

        Optional<Stock> optionalStock = stockRepository.findByNameAndDate(dto.getName(), dto.getDate());

        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = stockMapper.toEntity(dto);
        stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }

    @Transactional
    public StockDTO update(StockDTO dto){

        Optional<Stock> optionalStock = stockRepository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());

        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        Stock stock = stockMapper.toEntity(dto);
        stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }

    @Transactional
    public List<StockDTO> findAll() {
        List<Stock> list = stockRepository.findAll();
        return stockMapper.toDto(list);
    }

    @Transactional
    public StockDTO findById(Long id) {
        return stockRepository.findById(id).map(stockMapper::toDto).orElseThrow(NotFoundException::new);
    }

    @Transactional()
    public StockDTO delete(Long id) {
        StockDTO dto = this.findById(id);
        stockRepository.deleteById(id);
        return dto;
    }

    @Transactional()
    public List<StockDTO> findByToday(){
        return  stockRepository.findByToday(LocalDate.now()).map(stockMapper::toDto).orElseThrow(NotFoundException::new);
    }

}
