package com.threem.carrental.app.repository.expressionBuilder;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.threem.carrental.app.model.dto.CarSearchDto;
import com.threem.carrental.app.model.entity.AddressBranchEntity;
import com.threem.carrental.app.model.entity.CarEntity;
import com.threem.carrental.app.model.entity.QCarEntity;
import com.threem.carrental.app.model.entity.enumTypes.*;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author marek_j on 2018-06-22
 */
public class QCarExpressionBuilder {

    private BooleanExpression booleanExpression;
    private Boolean hasExpression;

    public QCarExpressionBuilder(Builder builder) {
        this.booleanExpression = builder.booleanExpression;
        this.hasExpression = builder.hasExpression;
    }

    public BooleanExpression getExpression() {return booleanExpression;}

    public Boolean hasExpression() {return hasExpression;}


    public static class Builder {

        private BooleanExpression booleanExpression;
        private Boolean hasExpression = false;

        public Builder carSearchDto(CarSearchDto carSearchDto) {
            if (carSearchDto!=null) {
                CarEntity carSearchEntity = carSearchDto.getCarEntity();
                QCarExpressionBuilder builder = new QCarExpressionBuilder.Builder() //todo refactor builder to have constructor with dto
                        .id(carSearchEntity.getId())
                        .vin(carSearchEntity.getVin())
                        .make(carSearchEntity.getMake())
                        .model(carSearchEntity.getModel())
                        .bodyType(carSearchEntity.getBodyType())
                        .yearExact(carSearchEntity.getYear())
                        .yearBetween(carSearchDto.getYearFrom(),carSearchDto.getYearTo())
                        .capacityExact(carSearchEntity.getEngineCapacity())
                        .capacityBetween(carSearchDto.getCapacityFrom(),carSearchDto.getCapacityTo())
                        .seatsExact(carSearchEntity.getSeats())
                        .seatsBetween(carSearchDto.getSeatsFrom(),carSearchDto.getSeatsTo())
                        .doorsExact(carSearchEntity.getDoors())
                        .doorsBetween(carSearchDto.getDoorsFrom(),carSearchDto.getDoorsTo())
                        .dailyRateExact(carSearchEntity.getDailyRate())
                        .dailyRateBetween(carSearchDto.getDailyRateFrom(),carSearchDto.getDailyRateTo())
                        .colour(carSearchEntity.getColour())
                        .status(carSearchEntity.getStatus())
                        .engineType(carSearchEntity.getEngineType())
                        .segment(carSearchEntity.getSegment())
                        .equipment(carSearchEntity.getEquipment())
                        .addressCity(carSearchEntity.getBranch().getAddress())
                        .build();

                if (builder.hasExpression()) {
                    this.hasExpression = true;
                    this.booleanExpression = builder.getExpression();
                }
            }
            return this;
        }

        public Builder id(Long id) {
            if (id!=null) {
                BooleanExpression expression = QCarEntity.carEntity.id.eq(id);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder vin(String vin) {
            if (!StringUtils.isEmpty(vin)) {
                BooleanExpression expression = QCarEntity.carEntity.vin.like(vin);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder make(String make) {
            if (!StringUtils.isEmpty((make))) {
                BooleanExpression expression = QCarEntity.carEntity.make.like(make);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder model(String model) {
            if (!StringUtils.isEmpty((model))) {
                BooleanExpression expression = QCarEntity.carEntity.model.like(model);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder bodyType(CarBodyTypeEnum bodyType) {
            if (bodyType!=null) {
                BooleanExpression expression = QCarEntity.carEntity.bodyType.eq(bodyType);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder yearExact(Integer year) {
            if (year!=null) {
                BooleanExpression expression = QCarEntity.carEntity.year.eq(year);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder yearBetween(Integer yearFrom, Integer yearTo) {
            if (yearFrom!=null || yearTo!=null) {
                BooleanExpression expression = QCarEntity.carEntity.year.between(yearFrom,yearTo);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }


        public Builder capacityExact(Integer capacity) {
            if (capacity!=null) {
                BooleanExpression expression = QCarEntity.carEntity.year.eq(capacity);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder capacityBetween(Integer capacityFrom, Integer capacityTo) {
            if (capacityFrom!=null || capacityTo!=null) {
                BooleanExpression expression = QCarEntity.carEntity.engineCapacity.between(capacityFrom,capacityTo);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder seatsExact(Integer seats) {
            if (seats!=null) {
                BooleanExpression expression = QCarEntity.carEntity.seats.eq(seats);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder seatsBetween(Integer seatsFrom, Integer seatsTo) {
            if (seatsFrom!=null || seatsTo!=null) {
                BooleanExpression expression = QCarEntity.carEntity.seats.between(seatsFrom,seatsTo);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder doorsExact(Integer doors) {
            if (doors!=null) {
                BooleanExpression expression = QCarEntity.carEntity.doors.eq(doors);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder doorsBetween(Integer doorsFrom, Integer doorsTo) {
            if (doorsFrom!=null || doorsTo!=null) {
                BooleanExpression expression = QCarEntity.carEntity.doors.between(doorsFrom,doorsTo);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder dailyRateExact(BigDecimal dailyRate) {
            if (dailyRate!=null) {
                BooleanExpression expression = QCarEntity.carEntity.dailyRate.eq(dailyRate);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder dailyRateBetween(BigDecimal dailyRateFrom, BigDecimal dailyRateTo) {
            if (dailyRateFrom!=null || dailyRateTo!=null) {
                BooleanExpression expression = QCarEntity.carEntity.dailyRate.between(dailyRateFrom,dailyRateTo);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder colour(CarColourEnum colour) {
            if (colour!=null) {
                BooleanExpression expression = QCarEntity.carEntity.colour.eq(colour);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder status(CarStatusEnum status) {
            if (status!=null) {
                BooleanExpression expression = QCarEntity.carEntity.status.eq(status);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder engineType(CarEngineTypeEnum engineType) {
            if (engineType!=null) {
                BooleanExpression expression = QCarEntity.carEntity.engineType.eq(engineType);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder segment(CarSegmentTypeEnum segment) {
            if (segment!=null) {
                BooleanExpression expression = QCarEntity.carEntity.segment.eq(segment);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder equipment(List<CarEquipmentEnum> equipment) {
            if (equipment!=null && equipment.size()>0) {
                for (CarEquipmentEnum currentEquipment : equipment) { //todo refactor to lambda
                    BooleanExpression expression = QCarEntity.carEntity.equipment.contains(currentEquipment);
                    this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                    this.hasExpression = true;
                }
            }
            return this;
        }

        public Builder addressCity(AddressBranchEntity addressBranchEntity) {
            if (addressBranchEntity!=null || !StringUtils.isEmpty(addressBranchEntity.getCity())) {
                String city = addressBranchEntity.getCity();
                BooleanExpression expression = QCarEntity.carEntity.branch.address.city.like(city);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,expression);
                this.hasExpression = true;
            }
            return this;
        }

        public QCarExpressionBuilder build() {
            return new QCarExpressionBuilder(this);
        }

        private BooleanExpression initOrUpdateExpression(BooleanExpression originalExpr, BooleanExpression newExpr) {
            BooleanExpression resultExpression = null;
            if (originalExpr!=null) {
                resultExpression = originalExpr.and(newExpr);
            } else {
                resultExpression = newExpr;
            }
            return resultExpression;
        }
    }

}
