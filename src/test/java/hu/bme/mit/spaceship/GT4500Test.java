package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTSPrimary;
  private TorpedoStore mockTSSecondary;

  @BeforeEach
  public void init(){
    mockTSPrimary = mock(TorpedoStore.class);
    mockTSSecondary = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTSPrimary, mockTSSecondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(true);
    when(mockTSSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_SINGLE_twice_Success(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(true);
    when(mockTSSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result &= ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTSPrimary, times(1)).fire(1);
    verify(mockTSSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_SINGLE_twice_secondary_empty_Success(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(true);
    when(mockTSSecondary.fire(1)).thenReturn(true);
    when(mockTSSecondary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result &= ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTSPrimary, times(2)).fire(1);
    verify(mockTSSecondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_SINGLE_twice_primary_empty_Success(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(true);
    when(mockTSSecondary.fire(1)).thenReturn(true);
    when(mockTSPrimary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result &= ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTSPrimary, times(0)).fire(1);
    verify(mockTSSecondary, times(2)).fire(1);
  }

  @Test
  public void fireLaser_Success(){
    // Act
    boolean result = ship.fireLaser(FiringMode.SINGLE);

    // Assert
    // Not implemented method
    assertEquals(false, result);
  }
}
