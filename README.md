# CloudKitchen

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Configuration](#configuration)
6. [Testing](#unittesting)
7. [Result](#results)

## Introduction
CloudKitchen is a simulation of a kitchen management system, handling order processing, courier management, and statistical reporting. This project demonstrates how different components of a cloud kitchen interact to ensure efficient order fulfillment and delivery.

## Features
- Order Preparation and Management
- Courier Creation and Dispatch
- Statistical Reporting of Orders and Couriers
- Graceful Shutdown

## Installation
To install and run the project locally, follow these steps:

1. Clone the repository:
    ```bash
    git clone https://github.com/jai1/CloudKitchen.git
    cd CloudKitchen
    ```

2. Build the project using Maven:
    ```bash
    mvn clean install
    ```

3. Run the application:
    ```bash
    mvn exec:java -Dexec.mainClass="org.interview.cloud.kitchen.Main"
    ```

## Usage
The application simulates a cloud kitchen where orders are prepared and dispatched by couriers. Key classes include:

- `OrderManager`: Manages orders from creation to dispatch.
- `KitchenManager`: Handles order preparation.
- `CourierManager`: Manages courier creation and dispatch.
- `StatsManager`: Collects and reports statistics on orders and couriers.
- `Dispatcher`: Manages the dispatch strategy.
- `MatchedDispatcher`: A courier is dispatched for a specific order and may only pick up that order
- `First-in-first-out`: A courier picks up the next available order upon arrival. If there are
                        multiple orders available, pick up an arbitrary order. If there are no available orders,
                        couriers wait for the next available one. When there are multiple couriers waiting, the
                        next available order is assigned to the earliest arrived courier.

### Note
To change between the dispatch strategies, change the `dispatchStrategyClassName` in `application.properties`

## Configuration
Configuration details can be customized in the application properties file. Update the properties as needed to fit your environment.

## UnitTesting
Unit tests are provided to ensure the functionality of the system. To run the tests:

```bash
mvn test
```

## Results

- `First-in-first-out`
StatsManager - orderCount = 132, orderWaitTimeInMillis = 50665, courierCount = 132, courierWaitTimeInMillis = 53536
StatsManager - Average order waitTime in Millis = 383
StatsManager - Average courier waitTime in Millis = 405

- `MatchedDispatcher`
StatsManager - orderCount = 132, orderWaitTimeInMillis = 33284, courierCount = 132, courierWaitTimeInMillis = 41295
StatsManager - Average order waitTime in Millis = 252
StatsManager - Average courier waitTime in Millis = 312