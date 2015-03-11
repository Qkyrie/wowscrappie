package com.deswaef.weakauras.raids.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("WORLDBOSS")
@Entity
public class WorldBoss extends Boss{
}
