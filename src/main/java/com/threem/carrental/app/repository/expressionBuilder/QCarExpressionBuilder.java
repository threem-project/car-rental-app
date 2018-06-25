package com.threem.carrental.app.repository.expressionBuilder;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.threem.carrental.app.model.entity.AddressBranchEntity;
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

        public Builder id(Long id) {
            if (id!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.id.eq(id);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder vin(String vin) {
            if (!StringUtils.isEmpty(vin)) {
                BooleanExpression newExpr = QCarEntity.carEntity.vin.like(vin);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder make(String make) {
            if (!StringUtils.isEmpty((make))) {
                BooleanExpression newExpr = QCarEntity.carEntity.make.like(make);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder model(String model) {
            if (!StringUtils.isEmpty((model))) {
                BooleanExpression newExpr = QCarEntity.carEntity.model.like(model);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder bodyType(CarBodyTypeEnum bodyType) {
            if (bodyType!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.bodyType.eq(bodyType);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder yearExact(Integer year) {
            if (year!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.year.eq(year);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder yearBetween(Integer yearFrom, Integer yearTo) {
            if (yearFrom!=null || yearTo!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.year.between(yearFrom,yearTo);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }


        public Builder capacityExact(Integer capacity) {
            if (capacity!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.year.eq(capacity);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder capacityBetween(Integer capacityFrom, Integer capacityTo) {
            if (capacityFrom!=null || capacityTo!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.engineCapacity.between(capacityFrom,capacityTo);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder seatsExact(Integer seats) {
            if (seats!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.seats.eq(seats);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder seatsBetween(Integer seatsFrom, Integer seatsTo) {
            if (seatsFrom!=null || seatsTo!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.seats.between(seatsFrom,seatsTo);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder doorsExact(Integer doors) {
            if (doors!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.doors.eq(doors);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder doorsBetween(Integer doorsFrom, Integer doorsTo) {
            if (doorsFrom!=null || doorsTo!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.doors.between(doorsFrom,doorsTo);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder dailyRateExact(BigDecimal dailyRate) {
            if (dailyRate!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.dailyRate.eq(dailyRate);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder dailyRateBetween(BigDecimal dailyRateFrom, BigDecimal dailyRateTo) {
            if (dailyRateFrom!=null || dailyRateTo!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.dailyRate.between(dailyRateFrom,dailyRateTo);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder colour(CarColourEnum colour) {
            if (colour!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.colour.eq(colour);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder status(CarStatusEnum status) {
            if (status!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.status.eq(status);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder engineType(CarEngineTypeEnum engineType) {
            if (engineType!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.engineType.eq(engineType);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder segment(CarSegmentTypeEnum segment) {
            if (segment!=null) {
                BooleanExpression newExpr = QCarEntity.carEntity.segment.eq(segment);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                this.hasExpression = true;
            }
            return this;
        }

        public Builder equipment(List<CarEquipmentEnum> equipment) {
            if (equipment!=null && equipment.size()>0) {
                for (CarEquipmentEnum currentEquipment : equipment) { //todo refactor to lambda
                    BooleanExpression newExpr = QCarEntity.carEntity.equipment.contains(currentEquipment);
                    this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
                    this.hasExpression = true;
                }
            }
            return this;
        }

        public Builder addressCity(AddressBranchEntity addressBranchEntity) {
            if (addressBranchEntity!=null && !StringUtils.isEmpty(addressBranchEntity.getCity())) {
                String city = addressBranchEntity.getCity();
                BooleanExpression newExpr = QCarEntity.carEntity.branch.address.city.like(city);
                this.booleanExpression = initOrUpdateExpression(booleanExpression,newExpr);
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
