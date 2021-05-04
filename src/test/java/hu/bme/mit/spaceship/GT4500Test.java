package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore mockfirstTorpedoStore;
  private TorpedoStore mocksecondTorpedoStore;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    mockfirstTorpedoStore = mock(TorpedoStore.class);
    mocksecondTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(mockfirstTorpedoStore,mocksecondTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockfirstTorpedoStore.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockfirstTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_TwoTimes_Success(){
    // Arrange
    when(mockfirstTorpedoStore.fire(1)).thenReturn(true);
    when(mocksecondTorpedoStore.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockfirstTorpedoStore, times(1)).fire(1);
    verify(mocksecondTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_FirstEmpty_Success(){
    // Arrange
    when(mockfirstTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockfirstTorpedoStore, times(0)).fire(1);
    verify(mocksecondTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_BothEmpty_Success(){
    // Arrange
    when(mockfirstTorpedoStore.isEmpty()).thenReturn(true);
    when(mocksecondTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(result, false);
    verify(mockfirstTorpedoStore, times(0)).fire(1);
    verify(mocksecondTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_First_TwoTimes_Success(){
    // Arrange
    when(mocksecondTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockfirstTorpedoStore, times(2)).fire(1);
    verify(mocksecondTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_First_TwoTimes_Fail(){
    // Arrange
    when(mocksecondTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    when(mockfirstTorpedoStore.isEmpty()).thenReturn(true);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockfirstTorpedoStore, times(1)).fire(1);
    verify(mocksecondTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockfirstTorpedoStore.fire(1)).thenReturn(true);
    when(mocksecondTorpedoStore.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockfirstTorpedoStore, times(1)).fire(1);
    verify(mocksecondTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_FirstEmpty_Fail(){
    // Arrange
    when(mockfirstTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(result, false);
    verify(mockfirstTorpedoStore, times(0)).fire(1);
    verify(mocksecondTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_SecondEmpty_Fail(){
    // Arrange
    when(mocksecondTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(result, false);
    verify(mockfirstTorpedoStore, times(0)).fire(1);
    verify(mocksecondTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_AllEmpty_Fail(){
    // Arrange
    when(mockfirstTorpedoStore.isEmpty()).thenReturn(true);
    when(mocksecondTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(result, false);
    verify(mockfirstTorpedoStore, times(0)).fire(1);
    verify(mocksecondTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Egyeb_Fail(){
    // Arrange
    when(mockfirstTorpedoStore.isEmpty()).thenReturn(true);
    when(mocksecondTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.Egyeb);

    // Assert
    assertEquals(result, false);
    verify(mockfirstTorpedoStore, times(0)).fire(1);
    verify(mocksecondTorpedoStore, times(0)).fire(1);
  }

}
